package com.furan.aommessage.controller;

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

import com.furan.aommessage.entity.NewsEntity;
import com.furan.aommessage.service.NewsService;



/**
 * 行业动态表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-15 19:23:11
 */
@RestController
@RequestMapping("aommessage/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("aommessage:news:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = newsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("aommessage:news:info")
    public R info(@PathVariable("id") Integer id){
		NewsEntity news = newsService.getById(id);

        return R.ok().put("news", news);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("aommessage:news:save")
    public R save(@RequestBody NewsEntity news){
		newsService.save(news);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("aommessage:news:update")
    public R update(@RequestBody NewsEntity news){
		newsService.updateById(news);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("aommessage:news:delete")
    public R delete(@RequestBody Integer[] ids){
		newsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/listpending")
    @RequiresPermissions("aommessage:courses:list")
    public R judge(@RequestParam  Map<String, Object> params){
        PageUtils page = newsService.listJPending(params);

        return R.ok().put("page", page);
    }


    @RequestMapping("/listbyuserid")
    @RequiresPermissions("aommessage:news:list")
    public R listByUserId(@RequestParam Map<String, Object> params){
        PageUtils page = newsService.queryPageByUserId(params);

        return R.ok().put("page", page);
    }


}
