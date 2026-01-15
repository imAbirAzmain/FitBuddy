package org.fitbuddy.util;

import org.fitbuddy.gateway.*;
import org.fitbuddy.service.AuthService;
import org.fitbuddy.service.DailyLogService;

public class AppContext {

    // ✅ Tomorrow: swap these to DAO-backed gateways (SqlAuthGateway / SqlDailyLogGateway)
    private static final AuthGateway AUTH_GATEWAY = new InMemoryAuthGateway();
    private static final DailyLogGateway LOG_GATEWAY = new InMemoryDailyLogGateway();

    private static final AuthService AUTH_SERVICE = new AuthService(AUTH_GATEWAY);
    private static final DailyLogService LOG_SERVICE = new DailyLogService(LOG_GATEWAY);

    public static AuthService auth() { return AUTH_SERVICE; }
    public static DailyLogService logs() { return LOG_SERVICE; }
}
