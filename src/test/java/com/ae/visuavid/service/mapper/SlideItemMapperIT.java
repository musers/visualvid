package com.ae.visuavid.service.mapper;

import com.ae.visuavid.VisualvidApp;
import com.ae.visuavid.domain.SlideItemEntity;
import com.ae.visuavid.service.dto.SlideItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = VisualvidApp.class)
@ActiveProfiles(profiles = "local")
public class SlideItemMapperIT {
    @Autowired
    private SlideItemMapper mapper;

    @Test
    public void testMapper() {
        SlideItemDTO dto = new SlideItemDTO();
        dto.setLabel("test Label");
        SlideItemEntity entity = mapper.toEntity(dto);
    }
}
