package com.furan.mettings.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.furan.mettings.entity.MeetingsEntity;
import com.furan.mettings.service.MeetingsService;




/**
 * 会议信息表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-15 22:18:37
 */
@RestController
@RequestMapping("mettings/meetings")
public class MeetingsController {
    @Autowired
    private MeetingsService meetingsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("mettings:meetings:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = meetingsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("mettings:meetings:info")
    public R info(@PathVariable("id") Integer id){
		MeetingsEntity meetings = meetingsService.getById(id);

        return R.ok().put("meetings", meetings);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("mettings:meetings:save")
    public R save(@RequestBody MeetingsEntity meetings){
		meetingsService.save(meetings);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("mettings:meetings:update")
    public R update(@RequestBody MeetingsEntity meetings){
		meetingsService.updateById(meetings);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("mettings:meetings:delete")
    public R delete(@RequestBody Integer[] ids){
		meetingsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
