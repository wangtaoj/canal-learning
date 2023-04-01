package com.wangtao.canal.rocketmq.processor;

import com.wangtao.canal.rocketmq.constant.CanalTableModel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangtao
 * Created at 2023/4/1 15:38
 */
@Component
public class CanalProcessorContainer implements SmartInitializingSingleton, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private final Map<CanalTableModel, ICanalProcessor> cache = new HashMap<>();

    public void register(CanalTableModel tableModel, ICanalProcessor canalProcessor) {
        cache.put(tableModel, canalProcessor);
    }

    public ICanalProcessor get(CanalTableModel tableModel) {
        ICanalProcessor canalProcessor = cache.get(tableModel);
        if (canalProcessor == null) {
            throw new IllegalArgumentException(tableModel.toString());
        }
        return canalProcessor;
    }

    @Override
    public void afterSingletonsInstantiated() {
        @SuppressWarnings({"rawtypes"})
        Map<String, AbstractCanalProcessor> canalProcessorMap = applicationContext.getBeansOfType(AbstractCanalProcessor.class);
        CanalMessageParser canalMessageParser = applicationContext.getBean(CanalMessageParser.class);
        canalProcessorMap.forEach((k, processor) -> {
            processor.init(canalMessageParser);
            this.register(processor.support(), processor);
        });
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
