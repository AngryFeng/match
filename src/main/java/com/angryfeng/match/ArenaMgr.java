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
    private Map<Integer, MatchBean> applieds = new ConcurrentHashMap<Integer, MatchBean>();
    private Map<Integer, Arena> arenaMap = new ConcurrentHashMap<Integer, Arena>();

    public void match(int playerId) {
        MatchBean visitor = new MatchBean(playerId, System.currentTimeMillis());
        List<MatchBean> collect = applieds.values().stream().sorted(Comparator.comparingLong(MatchBean::getMatchBeginTime)).collect(Collectors.toList());
        MatchBean host = null;
        for (MatchBean matchBean : collect) {
            if (matchBean.canMatch(visitor)) {
                host = matchBean;
            }
        }
        if (Objects.nonNull(host)) {
            applieds.put(playerId, visitor);
        }
    }
}
