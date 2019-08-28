package ru.atom.thread.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author apomosov
 * @since 15.03.17
 */
public class EventProcessor {
    private static List <EventProducer> eventProducersList = new ArrayList<>();
    private static long  countGood = 0;
    private static  long countBad = 0;

    public static void produceEvents(List<EventProducer> eventProducers) {
        eventProducersList = eventProducers;
        eventProducersList.forEach(eventProducer -> {
                    eventProducer.run();
        });

        if(EventQueue.getInstance().peek()!=null) {
            getCountByType(EventQueue.getInstance());
        }
//        throw new UnsupportedOperationException();
    }

    public static long countTotalNumberOfGoodEvents() {
//        if(EventQueue.getInstance().peek()!=null) {
//            getCountByType(EventQueue.getInstance());
//        }
        return countGood;
    }

    public static long countTotalNumberOfBadEvents() {
//        if(EventQueue.getInstance().peek()!=null) {
//            getCountByType(EventQueue.getInstance());
//        }
        return countBad;
    }


    private static void getCountByType(BlockingQueue<Event> queue){
        long cGood = 0;
        long cBad = 0;

        while (queue.size() != 0) {

            Event event = null;
            try {
                event = queue.poll(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(Thread.currentThread().getName() + event.getEventType()+ " result: " + cGood +" "+cBad);

                if (event.getEventType().equals(Event.EventType.GOOD)) {
                    cGood++;
                }
                else if (event.getEventType().equals(Event.EventType.BAD))
                {
                    cBad++;
                }

        }
//        else
//        {
            countBad = cBad;
            countGood = cGood;
//        }


    }


}
