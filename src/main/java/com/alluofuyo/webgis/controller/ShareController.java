package com.alluofuyo.webgis.controller;


import com.alluofuyo.webgis.model.Position;
import com.alluofuyo.webgis.model.SharePoint;
import com.alluofuyo.webgis.service.ShareService;
import com.alluofuyo.webgis.utils.Message;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/share")
public class ShareController {
    final
    ShareService shareService;

    public ShareController(ShareService shareService) {
        this.shareService = shareService;
    }

    @PostMapping("/add")
    @ResponseBody
    public Message addShare(@RequestBody SharePoint sharePoint) {
        System.out.println("Receive Request:" + sharePoint);
        if (sharePoint != null) {
            Position position = shareService.addSharePoint(sharePoint);
            if (position == null) {
                return new Message().error("添加失败");
            }
            Message message = new Message();
            message.getData().put("position", position);
            message.success("添加成功");
            return message;
        } else {
            return new Message().error("添加失败");
        }
    }

    @GetMapping("/points")
    @ResponseBody
    public Message getPoints(@RequestParam(value = "id", required = false, defaultValue = "-1") Integer pointId) {
        if (pointId == -1) {
            List<Position> allPoints = shareService.getAllPoints();
            Message message = new Message();
            message.getData().put("points", allPoints);
            message.success("获取成功");
            return message;
        } else {
            SharePoint point = shareService.getPointById(pointId);
            Message message = new Message();
            message.getData().put("point", point);
            message.success("获取成功");
            return message;
        }
    }

    @GetMapping("/allShare")
    @ResponseBody
    public Message getSharePoints() {
        List<SharePoint> allShares = shareService.getAllShares();
        Message message = new Message();
        message.getData().put("shares", allShares);
        return message.success("获取成功");
    }

    @GetMapping("/search")
    @ResponseBody
    public Message searchShare(@RequestParam(value = "keyword", defaultValue = "") String keywords) {
        Message message = new Message();
        HashMap<String, Object> map = new HashMap<>();
        if ("".equals(keywords)){
            List<SharePoint> allShares = shareService.getAllShares();
            map.put("searchResult",allShares);
            message.setData(map);
            message.success("");
        }else {
            List<SharePoint> result= shareService.searchShares(keywords);
            map.put("searchResult",result);
            message.setData(map);
            message.success("");
        }
        return message;
    }
}
