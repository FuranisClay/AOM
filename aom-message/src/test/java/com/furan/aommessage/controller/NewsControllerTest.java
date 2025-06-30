package com.furan.aommessage.controller;

import com.furan.aommessage.entity.NewsEntity;
import com.furan.aommessage.service.NewsService;
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
class NewsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NewsService newsService;

    @InjectMocks
    private NewsController newsController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(newsController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void testList() throws Exception {
        // 准备测试数据

        mockMvc.perform(get("/aommessage/news/list")
                        .param("page", "1")
                        .param("limit", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.page").exists());

        verify(newsService).queryPage(any());
    }

    @Test
    void testInfo() throws Exception {
        NewsEntity news = new NewsEntity();
        news.setId(1);

        when(newsService.getById(1)).thenReturn(news);

        mockMvc.perform(get("/aommessage/news/info/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.news.id").value(1));

        verify(newsService).getById(1);
    }

    @Test
    void testSave() throws Exception {
        mockMvc.perform(post("/aommessage/news/save")
                        .contentType("application/json")
                        .content("{\"title\":\"Test News\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));

        verify(newsService).save(any(NewsEntity.class));
    }

    @Test
    void testUpdate() throws Exception {
        mockMvc.perform(post("/aommessage/news/update")
                        .contentType("application/json")
                        .content("{\"id\":1,\"title\":\"Updated News\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));

        verify(newsService).updateById(any(NewsEntity.class));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(post("/aommessage/news/delete")
                        .contentType("application/json")
                        .content("[1,2,3]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));

        verify(newsService).removeByIds(Arrays.asList(1, 2, 3));
    }

    @Test
    void testJudgePendingList() throws Exception {

        mockMvc.perform(get("/aommessage/news/listpending")
                        .param("page", "1")
                        .param("limit", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.page").exists());

        verify(newsService).listJPending(any());
    }

    @Test
    void testListByUserId() throws Exception {

        mockMvc.perform(get("/aommessage/news/listbyuserid")
                        .param("page", "1")
                        .param("limit", "10")
                        .param("userId", "123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.page").exists());

        verify(newsService).queryPageByUserId(any());
    }

    @Test
    void testPermissionDenied() throws Exception {
        doThrow(new AuthorizationException()).when(newsService).queryPage(any());

        mockMvc.perform(get("/aommessage/news/list"))
                .andExpect(status().isForbidden());
    }
}

class GlobalExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(AuthorizationException.class)
    public R handleAuthorizationException() {
        return R.error(403, "没有权限，请联系管理员授权");
    }
}