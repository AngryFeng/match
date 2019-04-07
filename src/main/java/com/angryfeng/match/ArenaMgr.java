package com.angryfeng.match;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2019/4/7 0007.
 */
public class ArenaMgr {
    private Map<Integer, MatchBean> applieds = new ConcurrentHashMap<>();
    private Map<Integer, Arena> arenaMap = new ConcurrentHashMap<>();

    public void match(int playerId) {
        if (!applieds.isEmpty()) {
            MatchBean visitor = new MatchBean(playerId, System.currentTimeMillis());
            List<MatchBean> collect = applieds.values().stream().sorted(Comparator.comparingLong(MatchBean::getMatchBeginTime)).collect(Collectors.toList());
            MatchBean host = null;
            for (MatchBean matchBean : collect) {
                //第二档匹配成功
                //第一档匹配成功
                if (matchBean.canMatch()) {
                    host = matchBean;
                    break;
                }
            }
            if (Objects.isNull(host)) {
                applieds.put(playerId, visitor);
            } else {
            }
        }
    }

    private void clearTimeoutApplieds() {
        List<MatchBean> collect = applieds.values().stream().sorted(Comparator.comparingLong(MatchBean::getMatchBeginTime)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            for (MatchBean matchBean : collect) {
                if (20 * 1000 < System.currentTimeMillis() - matchBean.getMatchBeginTime()) {
                    break;
                }
                //匹配ai
            }
        }
    }
}
