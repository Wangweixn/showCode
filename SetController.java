package com.wqbr.gzoss.sys.web.setting;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wqbr.gzoss.sys.base.domain.Common;
import com.wqbr.gzoss.sys.base.service.CommonService;
import com.wqbr.gzoss.sys.common.util.CommValidate;
import com.wqbr.gzoss.sys.setting.domain.Seting;
import com.wqbr.gzoss.sys.setting.service.SetService;
import com.wqbr.gzoss.sys.system.domain.SysUser;
import com.wqbr.gzoss.sys.system.domain.UnitUser;
import com.wqbr.gzoss.sys.system.service.UserService;
import com.wqbr.gzoss.sys.web.base.BaseController;

@RequestMapping(value = "/set")
@Controller
public class SetController extends BaseController {

	@Resource
	private CommonService commonService;
	
	@Resource
	private SetService setService;
	
	@Resource
	private UserService userService;
	
	/**
	 *@作者:Mr.WangWX
	 *@日期:2018年9月30日
	 *@描述:页面跳转
	 */
	@RequestMapping("/tjdj")
	public ModelAndView page(HttpServletRequest request, Seting vo) {
		try {
			ModelAndView model = new ModelAndView("setting/page");
			SysUser sysUser = getLoginUser(request);
			String district = sysUser.getDistrict();
			vo.setDistrict(district);
			vo.setUsername(sysUser.getUsername());
			// 查询行政区划页面展示
			Common common = new Common();
			common.setDmbh("YAB003");
			List<Common> yab003S=commonService.findAllCommon(common);
			List<Seting> list = setService.findSetByCondition(vo);
			int totalCount = setService.findSetNumByCondition(vo);
			vo.setTotalCount(totalCount);
			model.addObject("list", list);
			model.addObject("yab003S", yab003S);
			model.addObject("vo", vo);
			return model;
		} catch (Exception e) {
			logger.error("设置体检登记跳转页面异常", e);
			return exception(e, request);
		}
	}
	
	/**
	 *@作者:Mr.WangWX
	 *@日期:2018年9月30日
	 *@描述:保存
	 */
	@RequestMapping("/insert")
	public ModelAndView insert(Seting vo,HttpServletRequest request){
		try {
			String sbjbjg = vo.getSbjbjg().split("\\*")[0];
			String name = vo.getSbjbjg().split("\\*")[1];
			vo.setSbjbjg(sbjbjg);
			vo.setName(name);
			//根据社保经办机构查询是否已经保存
			boolean isSave = setService.findSetBySbjbjg(vo);
			if(isSave){
				return ajaxDoneError("当前经办机构已经保存");
			}
			setService.insert(vo);
			return ajaxDoneSuccess("操作成功");
		} catch (Exception e) {
			logger.error("设置体检登记保存异常", e);
			return exception(e, request);
		}
	}
	
	/**
	 *@作者:Mr.WangWX
	 *@日期:2018年9月30日
	 *@描述:删除
	 */
	@RequestMapping("/delete")
	public ModelAndView delete(Seting vo,HttpServletRequest request){
		try {
			setService.delete(vo);
			return ajaxDoneSuccess("操作成功");
		} catch (Exception e) {
			logger.error("设置体检登记删除异常", e);
			return exception(e, request);
		}
	}
	
	/**
	 *@作者:Mr.WangWX
	 *@日期:2018年9月30日
	 *@描述:设置高危行业
	 */
	@RequestMapping("/high/{district}")
	public ModelAndView page(HttpServletRequest request,@PathVariable("district") String district,Seting vo) {
		try {
			ModelAndView model = new ModelAndView("setting/high");
			vo.setDistrict(district);
			model.addObject("vo", vo);
			return model;
		} catch (Exception e) {
			logger.error("设置高危行业跳转页面异常", e);
			return exception(e, request);
		}
	}
	
	/**
	 *@作者:Mr.WangWX
	 *@日期:2018年9月30日
	 *@描述:根据单位编号查询单位信息展示
	 */
	@RequestMapping("/dwxx")
	public ModelAndView dwxx(HttpServletRequest request,UnitUser vo){
		ModelAndView model = new ModelAndView("setting/high");
		try {
			UnitUser unitUser = userService.findUnitUserByDwbhAndSbjbjg(vo);
			if(unitUser==null){
				return ajaxDoneError("请输入正确的单位编号");
			}
			model.addObject("unitUser", unitUser);
			return model;
		} catch (Exception e) {
			logger.error("根据关键字查询单位信息", e);
			return exception(e, request);
		}
	}
	
	
	/**
	 *@作者:Mr.WangWX
	 *@日期:2018年10月8日
	 *@描述:
	 */
	@RequestMapping("/setHigh")
	public ModelAndView setHigh(HttpServletRequest request,UnitUser vo){
		try {
			//查询该单位是否属于高危行业
			UnitUser user = userService.findRemoteOrgnInfoByDwbh(vo);
			if(user!=null){
				String highrisk = CommValidate.isHighRisk(user.getAAB022());
				if("0".equals(highrisk)){
					return ajaxDoneError("对不起，该单位不属于高危行业，不能修改");
				}
				vo.setHighrisk("1");
				userService.setHigh(vo);
			}
			return ajaxDoneSuccess("操作成功");
		} catch (Exception e) {
			logger.error("修改为高危行业", e);
			return exception(e, request);
		}
	}
}