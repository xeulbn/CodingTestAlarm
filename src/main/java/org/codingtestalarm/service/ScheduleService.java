package org.codingtestalarm.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codingtestalarm.util.DiscordNotifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleService {

    private final DiscordNotifier notifier;

    private boolean isSkipDay() {
        // 공휴일 외부API 체크
        LocalDate today = LocalDate.now();
        return false;
    }

    private boolean isAllowedDay(){
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        return dayOfWeek.equals(DayOfWeek.MONDAY)||dayOfWeek.equals(DayOfWeek.TUESDAY)
                || dayOfWeek.equals(DayOfWeek.THURSDAY) ||  dayOfWeek.equals(DayOfWeek.FRIDAY);
    }

    private void send(String message){
        if(!isAllowedDay() || isSkipDay()){
            notifier.sendSimple(message);
            log.info("오늘은 코딩테스트 쉬는날입니다. 푹쉬고 앞으로 더 화이팅!!");
            return;
        }
        notifier.sendSimple(message);
    }

//    public void notifySkipDay(){
//        send("오늘은 코딩테스트 쉬는날입니다. 푹쉬고 앞으로 더 화이팅!!");
//    }

    @Scheduled(cron = "0 30 7 * * MON,TUE,THU,FRI", zone = "Asia/Seoul")
    public void notify1hBefore() {
        send("⏰ 코테 스터디 1시간 전입니다 (08:30 시작) !!");
    }

    @Scheduled(cron = "0 0 8 * * MON,TUE,THU,FRI",zone = "Asia/Seoul")
    public void notify30mBefore(){
        send("⏰ 코테 스터디 30분 전입니다 (08:30 시작) !!");
    }

    @Scheduled(cron = "0 20 8 * * MON,TUE,THU,FRI",zone = "Asia/Seoul")
    public void notify10mBefore(){
        send("⏰ 코테 스터디 10분 전입니다 (08:30 시작) -  — 자리 잡고 접속해주세요 \uD83D\uDE06");
    }
}
