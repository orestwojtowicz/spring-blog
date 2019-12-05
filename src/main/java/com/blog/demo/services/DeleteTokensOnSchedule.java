package com.blog.demo.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;

/**
 * Created by damiass on Oct, 2019
 */
@Service
@Transactional
public class DeleteTokensOnSchedule {

    @Scheduled(cron = "${purge.cron.expression}")
    public void deleteExpiredTokens() {
        Date now = Date.from(Instant.now());

        // ADD THIS FEATURE
       // tokenRepository.deleteAllExpiredSince(now);
    }

}
