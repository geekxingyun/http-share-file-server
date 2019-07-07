package com.xingyun.httpsharefileserver.repository;

import com.xingyun.httpsharefileserver.model.TempMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempMessageRepository extends JpaRepository<TempMessage,Long> {
    TempMessage findByMessageId(Long id);
}
