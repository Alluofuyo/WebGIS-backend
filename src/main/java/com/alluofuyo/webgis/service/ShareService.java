package com.alluofuyo.webgis.service;


import com.alluofuyo.webgis.mapper.PositionMapper;
import com.alluofuyo.webgis.mapper.SharePointMapper;
import com.alluofuyo.webgis.mapper.TagMapper;
import com.alluofuyo.webgis.model.Position;
import com.alluofuyo.webgis.model.SharePoint;
import com.alluofuyo.webgis.model.Tag;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShareService {
    @Resource
    SharePointMapper sharePointMapper;
    @Resource
    PositionMapper positionMapper;
    @Resource
    TagMapper tagMapper;


    public Position addSharePoint(SharePoint sharePointToAdd) {
        try {
            if (sharePointToAdd.getPosition() != null) {
                int i = positionMapper.insert(sharePointToAdd.getPosition());
            }
            sharePointToAdd.setPositionid(sharePointToAdd.getPosition().getId());
            sharePointMapper.insert(sharePointToAdd);
            if (sharePointToAdd.getTags().size() > 0) {
                sharePointToAdd.getTags().forEach((tag) -> {
                    if (!"".equals(tag.getTag())) {
                        tag.setShareid(sharePointToAdd.getId());
                        tagMapper.insert(tag);
                    }
                });
            }
            return sharePointToAdd.getPosition();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Position> getAllPoints() {
        return positionMapper.selectList(new QueryWrapper<>());
    }

    public SharePoint getPointById(Integer pointId) {
        if (pointId != null && pointId != -1) {
            SharePoint sharePoint = new SharePoint();
            Position position = positionMapper.selectById(pointId);
            sharePoint.setPositionid(pointId);
            SharePoint sharePoint1 = sharePointMapper.selectOne(new QueryWrapper<>(sharePoint));
            sharePoint1.setPosition(position);
            Tag tag = new Tag();
            tag.setShareid(sharePoint1.getId());
            List<Tag> tags = tagMapper.selectList(new QueryWrapper<>(tag));
            sharePoint1.setTags(tags);
            return sharePoint1;
        } else {
            return null;
        }
    }

    public List<SharePoint> getAllShares() {
        new QueryWrapper<>().exists()
        return sharePointMapper.selectList(new QueryWrapper<>());
    }
}
