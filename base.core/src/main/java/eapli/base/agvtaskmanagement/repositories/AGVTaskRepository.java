package eapli.base.agvtaskmanagement.repositories;

import eapli.base.agvtaskmanagement.domain.AGVTask;
import eapli.base.agvtaskmanagement.domain.AGVTaskId;
import eapli.framework.domain.repositories.DomainRepository;

public interface AGVTaskRepository extends DomainRepository<Long, AGVTask> {
}
