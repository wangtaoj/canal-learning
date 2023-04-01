package com.wangtao.canal.rocketmq.processor;

import com.wangtao.canal.rocketmq.constant.CanalEventType;
import lombok.*;

/**
 * @author wangtao
 * Created at 2023/4/1 15:18
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class CanalResult<T> {

    private T beforeData;

    private T afterData;

    private CanalEventType canalEventType;
}
