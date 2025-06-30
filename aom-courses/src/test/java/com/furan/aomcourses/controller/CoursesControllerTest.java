package com.furan.aomcourses.controller;

import com.furan.aomcourses.entity.CoursesEntity;
import com.furan.aomcourses.service.CoursesService;
import com.furan.common.utils.PageUtils;
import com.furan.common.utils.R;
import org.apache.shiro.authz.AuthorizationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CoursesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CoursesService coursesService;

    @InjectMocks
    private CoursesController coursesController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(coursesController)
                .setControllerAdvice(new GlobalExceptionHandler()) // 添加全局异常处理
                .build();
    }

    @Test
    void testList() throws Exception {
        // 准备测试数据
        Map<String, Object> params = new HashMap<>();

        // 模拟服务层行为

        // 执行并验证
        mockMvc.perform(get("/aomcourses/courses/list")
                        .param("page", "1")
                        .param("limit", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.page").exists());

        verify(coursesService).queryPage(any());
    }

    @Test
    void testInfo() throws Exception {
        // 准备测试数据
        CoursesEntity course = new CoursesEntity();
        course.setId(1);

        // 模拟服务层行为
        when(coursesService.getById(1)).thenReturn(course);

        // 执行并验证
        mockMvc.perform(get("/aomcourses/courses/info/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.courses.id").value(1));

        verify(coursesService).getById(1);
    }

    @Test
    void testSave() throws Exception {
        // 准备测试数据
        CoursesEntity course = new CoursesEntity();

        // 执行并验证
        mockMvc.perform(post("/aomcourses/courses/save")
                        .contentType("application/json")
                        .content("{\"name\":\"Test Course\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));

        verify(coursesService).save(any(CoursesEntity.class));
    }

    @Test
    void testUpdate() throws Exception {
        // 准备测试数据
        CoursesEntity course = new CoursesEntity();
        course.setId(1);

        // 执行并验证
        mockMvc.perform(post("/aomcourses/courses/update")
                        .contentType("application/json")
                        .content("{\"id\":1,\"name\":\"Updated Course\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));

        verify(coursesService).updateById(any(CoursesEntity.class));
    }

    @Test
    void testDelete() throws Exception {
        // 执行并验证
        mockMvc.perform(post("/aomcourses/courses/delete")
                        .contentType("application/json")
                        .content("[1,2,3]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));

        verify(coursesService).removeByIds(Arrays.asList(1, 2, 3));
    }

    @Test
    void testListPending() throws Exception {
        // 准备测试数据

        // 模拟服务层行为

        // 执行并验证
        mockMvc.perform(get("/aomcourses/courses/list/pending")
                        .param("page", "1")
                        .param("limit", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.page").exists());

        verify(coursesService).listJPending(any());
    }

    @Test
    void testListByAuthorId() throws Exception {
        // 准备测试数据

        // 模拟服务层行为

        // 执行并验证
        mockMvc.perform(get("/aomcourses/courses/list/author")
                        .param("page", "1")
                        .param("limit", "10")
                        .param("userId", "123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.page").exists());

        verify(coursesService).queryPageByUserId(any());
    }

    @Test
    void testPermissionDenied() throws Exception {
        // 模拟权限不足异常
        doThrow(new AuthorizationException()).when(coursesService).queryPage(any());

        mockMvc.perform(get("/aomcourses/courses/list"))
                .andExpect(status().isForbidden());
    }
}

// 简单的全局异常处理器用于测试
class GlobalExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(AuthorizationException.class)
    public R handleAuthorizationException() {
        return R.error(403, "没有权限，请联系管理员授权");
    }
}