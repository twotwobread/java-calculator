package com.programmers.calculator.storage;

import com.programmers.calculator.entity.Operation;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OperationMemoryStorageTest {

    private OperationStorage operationStorage;

    @BeforeEach
    void beforeEach() {
        operationStorage = new OperationMemoryStorage();
    }

    @Test
    @DisplayName("연산결과를 저장한다.")
    void save() {
        // given
        Operation operation = new Operation("1 + 1", "2");

        // when
        operationStorage.save(operation);

        // then
        assertEquals(1L, operationStorage.totalCount());
    }

    @Test
    @DisplayName("id를 이용하여 결과를 조회한다.")
    void findById() {
        // given
        Operation operationA = new Operation("2 + 4", "6");
        Operation operationB = new Operation("5 + 4", "9");

        // when
        operationStorage.save(operationA);
        operationStorage.save(operationB);

        // then
        assertEquals(operationStorage.findById(0L).get(), operationA);
    }

    @Test
    @DisplayName("잘못된 id를 이용해 결과 조회시 Optional.empty() 반환한다.")
    void findByWrongId() {
        // given
        Operation operationA = new Operation("2 + 4", "6");

        // when
        operationStorage.save(operationA); // 내부에서 id 0으로 세팅됨.

        // then
        assertEquals(Optional.empty(), operationStorage.findById(1000));
    }

    @Test
    @DisplayName("저장소가 비어있는지를 확인한다.")
    void isEmpty() {
        assertEquals(true, operationStorage.isEmpty());
    }
}