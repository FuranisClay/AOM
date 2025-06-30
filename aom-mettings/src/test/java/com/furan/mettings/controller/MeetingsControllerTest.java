package com.furan.mettings.controller;

import com.furan.mettings.entity.MeetingsEntity;
import com.furan.mettings.service.MeetingsService;
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
class MeetingsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MeetingsService meetingsService;

    @InjectMocks
    private MeetingsController meetingsController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(meetingsController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void testListMeetings() throws Exception {

        mockMvc.perform(get("/mettings/meetings/list")
                        .param("page", "1")
                        .param("limit", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.page").exists());

        verify(meetingsService).queryPage(any());
    }

    @Test
    void testGetMeetingInfo() throws Exception {
        MeetingsEntity meeting = new MeetingsEntity();
        meeting.setId(1);
        when(meetingsService.getById(1)).thenReturn(meeting);

        mockMvc.perform(get("/mettings/meetings/info/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.meetings.id").value(1));

        verify(meetingsService).getById(1);
    }

    @Test
    void testCreateMeeting() throws Exception {
        mockMvc.perform(post("/mettings/meetings/save")
                        .contentType("application/json")
                        .content("{\"title\":\"Team Meeting\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));

        verify(meetingsService).save(any(MeetingsEntity.class));
    }

    @Test
    void testUpdateMeeting() throws Exception {
        mockMvc.perform(post("/mettings/meetings/update")
                        .contentType("application/json")
                        .content("{\"id\":1,\"title\":\"Updated Meeting\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));

        verify(meetingsService).updateById(any(MeetingsEntity.class));
    }

    @Test
    void testDeleteMeetings() throws Exception {
        mockMvc.perform(post("/mettings/meetings/delete")
                        .contentType("application/json")
                        .content("[1,2,3]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));

        verify(meetingsService).removeByIds(Arrays.asList(1, 2, 3));
    }

    @Test
    void testListPendingMeetings() throws Exception {

        mockMvc.perform(get("/mettings/meetings/listpending")
                        .param("page", "1")
                        .param("limit", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.page").exists());

        verify(meetingsService).listJPending(any());
    }

    @Test
    void testListMeetingsByUserId() throws Exception {

        mockMvc.perform(get("/mettings/meetings/listbyuserid")
                        .param("page", "1")
                        .param("limit", "10")
                        .param("userId", "123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.page").exists());

        verify(meetingsService).queryPageByUserId(any());
    }

    @Test
    void testListMeetingsByTime() throws Exception {

        mockMvc.perform(get("/mettings/meetings/listbytime")
                        .param("page", "1")
                        .param("limit", "10")
                        .param("startTime", "2025-06-01")
                        .param("endTime", "2025-06-30"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.page").exists());

        verify(meetingsService).queryPageByTime(any());
    }

    @Test
    void testPermissionDenied() throws Exception {
        doThrow(new AuthorizationException()).when(meetingsService).queryPage(any());

        mockMvc.perform(get("/mettings/meetings/list"))
                .andExpect(status().isForbidden());
    }
}

class GlobalExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(AuthorizationException.class)
    public R handleAuthorizationException() {
        return R.error(403, "没有权限，请联系管理员授权");
    }
}