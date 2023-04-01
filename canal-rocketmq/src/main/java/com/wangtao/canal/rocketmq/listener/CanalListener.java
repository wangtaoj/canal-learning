package com.wangtao.canal.rocketmq.listener;

import com.alibaba.otter.canal.protocol.FlatMessage;
import com.wangtao.canal.rocketmq.constant.CanalTableModel;
import com.wangtao.canal.rocketmq.processor.CanalProcessorContainer;
import com.wangtao.canal.rocketmq.processor.ICanalProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangtao
 * Created at 2023/3/31 21:34
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "canal-consumer", topic = "canal-topic")
public class CanalListener implements RocketMQListener<FlatMessage> {

    @Autowired
    private CanalProcessorContainer canalProcessorContainer;

    @Override
    public void onMessage(FlatMessage message) {
        CanalTableModel canalTableModel = CanalTableModel.of(message.getDatabase(), message.getTable());
        ICanalProcessor processor = canalProcessorContainer.get(canalTableModel);
        if (processor != null) {
            processor.process(message);
        }
    }
}
