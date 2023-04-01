package com.wangtao.canal.rocketmq.processor;

import com.alibaba.otter.canal.protocol.FlatMessage;
import com.wangtao.canal.rocketmq.constant.CanalTableModel;

/**
 * @author wangtao
 * Created at 2023/4/1 15:21
 */
public interface ICanalProcessor {

    void process(FlatMessage flatMessage);

    CanalTableModel support();
}
