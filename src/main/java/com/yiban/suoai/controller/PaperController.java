package com.yiban.suoai.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.forepojo.ForeLetter;
import com.yiban.suoai.forepojo.ForeLetterMessage;
import com.yiban.suoai.forepojo.ForeSpaceLetter;
import com.yiban.suoai.pojo.Letter;
import com.yiban.suoai.pojo.LetterMessage;
import com.yiban.suoai.service.LetterMessageService;
import com.yiban.suoai.service.LetterService;
import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.service.UserService;
import com.yiban.suoai.service.impl.RedisServiceImpl;
import com.yiban.suoai.util.MapHelper;
import com.yiban.suoai.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("paper")
@Api(value = "信笺")
public class PaperController {

    private static Logger logger = Logger.getLogger(PaperController.class);// 添加日志

    @Autowired
    RedisService redisService;
    @Autowired
    LetterService letterService;
    @Autowired
    LetterMessageService letterMessageService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "letter",method = RequestMethod.GET)
    @ApiOperation(value = "获取自己信笺",notes = "获取自己信笺")
    @ResponseBody
    public Map<String,Object> getList(
            @RequestHeader(value = "token") @ApiParam(value = "验证") String token,
            @RequestParam("startPage") @ApiParam(value = "起始页") Integer start

    ) throws SAException {
        int userId = redisService.getUserId(token);
        PageHelper.offsetPage(start * PageUtil.pageSize,  PageUtil.pageSize);

        List<Letter> list = letterService.getOrderByTime(userId);
        int total = (int) new PageInfo<>(list).getTotal();
        List<ForeLetter> data = letterService.full(list);
        Map map = MapHelper.success();
        map.put("data",data);
        map.put("page", PageUtil.getPage(total, list.size(), start));
        return map;
    }

    @RequestMapping(value = "letter",method = RequestMethod.POST)
    @ApiOperation(value = "新建信笺",notes = "新建信笺")
    @ResponseBody
    public Map<String,Object> writeLetter(
            @RequestHeader(value = "token") @ApiParam(value = "验证") String token,
            @RequestParam(value = "headLine") @ApiParam(value = "标题") String headLine,
            @RequestParam(value = "content") @ApiParam(value = "内容") String content,
            @RequestParam(value = "myself") @ApiParam(value = "1为仅自己可见  0为放进时空邮局") boolean myself,
            @RequestParam(value = "hide") @ApiParam(value = "1为身份隐藏  0为不隐藏") boolean hide
    ) throws SAException {
        int userId = redisService.getUserId(token);
        Letter letter = new Letter();
        letter.setContent(content);
        letter.setHeadline(headLine);
        letter.setUserId(userId);
        letter.setTime(new Date());
        letter.setHide(hide);
        letter.setMyself(myself);
        letterService.insert(letter);
        Map map = MapHelper.success();
        map.put("letterId",letter.getId());
        return map;
    }

    @RequestMapping(value = "letter",method = RequestMethod.PUT)
    @ApiOperation(value = "编辑更新信笺",notes = "编辑更新信笺")
    @ResponseBody
    public Map<String,Object> updateLetter(
            @RequestHeader(value = "token") @ApiParam(value = "验证") String token,
            @RequestParam(value = "letterId") @ApiParam(value = "信笺Id") Integer letterId,
            @RequestParam(value = "headLine") @ApiParam(value = "标题") String headLine,
            @RequestParam(value = "content") @ApiParam(value = "内容") String content,
            @RequestParam(value = "myself") @ApiParam(value = "1为仅自己可见  0为放进时空邮局") boolean myself,
            @RequestParam(value = "hide") @ApiParam(value = "1为身份隐藏  0为不隐藏") boolean hide
    ) throws SAException {
        int userId = redisService.getUserId(token);
        Letter letter = letterService.get(letterId);
        letter.setHeadline(headLine);
        letter.setContent(content);
        letter.setMyself(myself);
        letter.setHide(hide);
        letterService.update(letter);
        Map map = MapHelper.success();
        return map;
    }

    @RequestMapping(value = "letter",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除信笺",notes = "删除信笺")
    @ResponseBody
    public Map<String,Object> deleteLetter(
            @RequestHeader(value = "token") @ApiParam(value = "验证") String token,
            @RequestParam(value = "letterId") @ApiParam(value = "信笺Id") int letterId
    ) throws SAException {
        int userId = redisService.getUserId(token);
        Letter letter = letterService.get(letterId);
        letter.setIsDelete(true);
        letterService.update(letter);
        Map map = MapHelper.success();
        return map;
    }


    @RequestMapping(value = "getpaper",method = RequestMethod.GET)
    @ApiOperation(value = "从时空邮局获取信笺",notes = "从时空邮局获取信笺")
    @ResponseBody
    public Map getSpaceLetter(
            @RequestHeader(value = "token") @ApiParam(value = "验证") String token
    ) throws SAException {
        int userId = redisService.getUserId(token);
//        if(!redisService.getSpaceLimit(userId)) {//判断今天是否已经获取过时空邮局信笺 @todo 上线更改
            List<Letter> spaceLetter = letterService.getSpaceLetter();
            if (spaceLetter == null) {//如果当前时空邮局没信
                return MapHelper.error("时空邮局的信被领光啦");
            } else {
                if (spaceLetter.size() < 5) {//如果信少于5封
                    List<ForeSpaceLetter> foreSpaceLetters = letterService.full2(spaceLetter);
                    redisService.setSpaceLetterLimit(userId);
                    Map map = MapHelper.success();
                    map.put("data", foreSpaceLetters);
                    return map;
                } else {//如果大于5封
                    List<Letter> letters = new ArrayList<>();
                    for (int i = 0; i < 5; i++) {
                        Letter remove = spaceLetter.remove(new Random().nextInt(spaceLetter.size()));
                        letters.add(remove);
                    }
                    List<ForeSpaceLetter> foreSpaceLetters = letterService.full2(letters);

                    Map map = MapHelper.success();
                    map.put("data", foreSpaceLetters);
                    redisService.setSpaceLetterLimit(userId);
                    return map;
                }
            }
//        }else {
//            return MapHelper.error("今天已获取过信笺了");
//        }
    }


    @RequestMapping(value = "lettermessage",method = RequestMethod.PUT)
    @ApiOperation(value = "时空邮局留言",notes = "时空邮局留言")
    @ResponseBody
    public Map<String,Object> putComment(
            @RequestHeader(value = "token") @ApiParam(value = "验证") String token,
            @RequestParam(value = "letterId") @ApiParam(value = "信笺Id") int letterId,
            @RequestParam(value = "content") @ApiParam(value = "留言内容") String content) throws SAException {

        int userId = redisService.getUserId(token);
        //保存留言
        LetterMessage letterMessage = new LetterMessage();
        letterMessage.setLetterId(letterId);
        letterMessage.setTime(new Date());
        letterMessage.setUserId(userId);
        letterMessage.setMessage(content);
        letterMessageService.insert(letterMessage);
        //修改信笺已读
        Letter letter = letterService.get(letterId);
        letter.setIsRead(true);
        letterService.update(letter);

        //redis通知他
        redisService.addImformToRedis(letter.getUserId(), RedisServiceImpl.LetterMessage);//对方的userId
        Map map = MapHelper.success();
        map.put("letterMessage",letterMessage.getId());
        return map;
    }

    @RequestMapping(value = "lettermessage",method = RequestMethod.GET)
    @ApiOperation(value = "获取留言",notes = "获取留言")
    @ResponseBody
    public Map<String,Object> getComment(
            @RequestHeader(value = "token") @ApiParam(value = "验证") String token,
            @RequestParam(value = "letterId") @ApiParam(value = "信笺Id") Integer letterId,
            @RequestParam(value = "page") @ApiParam(value = "起始页") Integer start) throws SAException {

        int userId = redisService.getUserId(token);

        List<LetterMessage> letterMessages = letterMessageService.get(letterId);
        int total = (int) new PageInfo<>(letterMessages).getTotal();
        List<ForeLetterMessage> full = letterMessageService.full(letterMessages,userId);

        Map map = MapHelper.success();
        map.put("letterMessage",full);
        map.put("page",PageUtil.getPage(total, letterMessages.size(), start));
        return map;
    }
}
