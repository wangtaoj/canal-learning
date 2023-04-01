package com.wangtao.canal.rocketmq.processor.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import com.wangtao.canal.rocketmq.constant.CanalTableModel;
import com.wangtao.canal.rocketmq.processor.AbstractCanalProcessor;
import com.wangtao.canal.rocketmq.processor.CanalResult;
import com.wangtao.canal.rocketmq.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author wangtao
 * Created at 2023/4/1 15:18
 */
@Slf4j
@Component
public class UserCanalProcessor extends AbstractCanalProcessor<UserVO> {

    private static final String INDEX_NAME = "user";

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Override
    protected void doInsert(CanalResult<UserVO> canalResult) {
        UserVO user = canalResult.getAfterData();
        log.info("user insert: {}", user);
        IndexRequest<UserVO> indexRequest = new IndexRequest.Builder<UserVO>()
                .index(INDEX_NAME)
                .id(user.getId() + "")
                .document(user)
                .build();
        try {
            // 创建或者更新
            elasticsearchClient.index(indexRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doUpdate(CanalResult<UserVO> canalResult) {
        UserVO user = canalResult.getAfterData();
        log.info("user update: {}", user);
        IndexRequest<UserVO> indexRequest = new IndexRequest.Builder<UserVO>()
                .index(INDEX_NAME)
                .id(user.getId() + "")
                .document(user)
                .build();
        try {
            // 创建或者更新
            elasticsearchClient.index(indexRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(CanalResult<UserVO> canalResult) {
        UserVO user = canalResult.getAfterData();
        log.info("user delete: {}", user);

        DeleteRequest deleteRequest = new DeleteRequest.Builder()
                .index(INDEX_NAME)
                .id(user.getId() + "")
                .build();
        try {
            elasticsearchClient.delete(deleteRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CanalTableModel support() {
        return CanalTableModel.USER;
    }
}
