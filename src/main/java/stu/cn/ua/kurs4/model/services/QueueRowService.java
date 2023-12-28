package stu.cn.ua.kurs4.model.services;

import org.springframework.stereotype.Service;
import stu.cn.ua.kurs4.model.domain.QueueRow;
import stu.cn.ua.kurs4.model.domain.people.Client;
import stu.cn.ua.kurs4.model.domain.people.Operator;
import stu.cn.ua.kurs4.repositories.QueueRowRepo;
import stu.cn.ua.kurs4.repositories.people.ClientRepo;
import stu.cn.ua.kurs4.repositories.people.OperatorRepo;

import java.time.Duration;
import java.util.*;

@Service
public class QueueRowService {

    private final QueueRowRepo queueRowRepo;

    private final ClientRepo clientRepo;

    private final OperatorRepo operatorRepo;

    public QueueRowService(QueueRowRepo queueRowRepo, ClientRepo clientRepo, OperatorRepo operatorRepo) {
        this.queueRowRepo = queueRowRepo;
        this.clientRepo = clientRepo;
        this.operatorRepo = operatorRepo;
    }


    public QueueRow findById(Long id) {
        Optional<QueueRow> queueRowOptional = queueRowRepo.findById(id);
        return queueRowOptional.orElse(null);
    }

    public List<QueueRow> findAll() {
        List<QueueRow> queueRows = queueRowRepo.findAll();
        return queueRowRepo.findAll();
    }

    public QueueRow save(QueueRow queueRow) {
        List<Operator> operators = operatorRepo.findAll();
        List<Operator> completelyFreeOperators = new ArrayList<>();
        List<Operator> freeOperators = new ArrayList<>();
        List<QueueRow> queueRows = queueRowRepo.findAll();
        if (Objects.equals(queueRow.getEmail(), "")){
            queueRow.setEmail(null);
        }

        for (Operator operator : operators) {
            if(operator.getQueueRows().isEmpty()){
                completelyFreeOperators.add(operator);
            }
        }
        boolean free = true;
        Duration duration;

        for (QueueRow queueRow1 : queueRows) {
            if(Objects.equals(queueRow1.getClient().getId(), queueRow.getClient().getId())){
                for (QueueRow queueRow2 : queueRow1.getClient().getQueues()){
                    if (Objects.equals(queueRow2.getOperation(), queueRow.getOperation())){
                        return null;
                    }
                    if (Objects.equals(queueRow2.getDateTime(), queueRow.getDateTime())){
                        return null;
                    } else if (queueRow2.getDateTime().isAfter(queueRow.getDateTime())){
                        duration = Duration.between(queueRow.getDateTime(), queueRow2.getDateTime());
                        if (duration.toHours() < 1) {
                            return null;
                        }
                    } else if (queueRow.getDateTime().isAfter(queueRow2.getDateTime())){
                        duration = Duration.between(queueRow2.getDateTime(), queueRow.getDateTime());
                        if (duration.toHours() < 1) {
                            return null;
                        }
                    }
                }
            }
        }

        for (QueueRow queueRow1 : queueRows) {
            if (queueRow1.getDateTime().isAfter(queueRow.getDateTime())) {
                duration = Duration.between(queueRow.getDateTime(), queueRow1.getDateTime());
                if (duration.toHours() >= 1) {
                    free=true;
                    if (queueRow1.getOperator() != null) {
                        for (QueueRow queueRow2 : queueRow1.getOperator().getQueueRows()) {
                            if (queueRow2.getDateTime().isAfter(queueRow.getDateTime())) {
                                duration = Duration.between(queueRow.getDateTime(), queueRow2.getDateTime());

                                if (duration.toHours() < 1) {
                                    free = false;
                                    break;
                                }
                            } else if (queueRow.getDateTime().isAfter(queueRow2.getDateTime())) {
                                duration = Duration.between(queueRow2.getDateTime(), queueRow.getDateTime());
                                if (duration.toHours() < 1) {
                                    free = false;
                                    break;
                                }
                            }else {
                                free = false;
                                break;
                            }
                        }
                    }
                    if (free){
                        freeOperators.add(queueRow1.getOperator());
                    }
                }
            } else if (queueRow.getDateTime().isAfter(queueRow1.getDateTime())) {
                duration = Duration.between(queueRow1.getDateTime(), queueRow.getDateTime());
                if (duration.toHours() >= 1) {
                    if (queueRow1.getOperator() != null) {
                        free=true;
                        for (QueueRow queueRow2 : queueRow1.getOperator().getQueueRows()) {
                            if (queueRow2.getDateTime().isAfter(queueRow.getDateTime())) {
                                duration = Duration.between(queueRow.getDateTime(), queueRow2.getDateTime());

                                if (duration.toHours() < 1) {
                                    free = false;
                                    break;
                                }
                            } else if (queueRow.getDateTime().isAfter(queueRow2.getDateTime())) {
                                duration = Duration.between(queueRow2.getDateTime(), queueRow.getDateTime());
                                if (duration.toHours() < 1) {
                                    free = false;
                                    break;
                                }
                            }else {
                                free = false;
                                break;
                            }
                        }
                    }
                    if (free){
                        freeOperators.add(queueRow1.getOperator());
                    }
                }
            }

        }

        List<Operator> allOperators = new ArrayList<>();
        allOperators.addAll(completelyFreeOperators);
        allOperators.addAll(freeOperators);
        if (allOperators.isEmpty()) {
            return null;
        }
        queueRow.setOperator(allOperators.get(0));
        return queueRowRepo.save(queueRow);
    }

    public void deleteById(Long id) {
        queueRowRepo.deleteById(id);
    }

    public QueueRow update(QueueRow queueRow, Long id) {
        QueueRow queueRow1 = queueRowRepo.getById(id);
        //queueRow.set(queueRow1.get)
        queueRowRepo.save(queueRow1);
        return queueRow;
    }
}
