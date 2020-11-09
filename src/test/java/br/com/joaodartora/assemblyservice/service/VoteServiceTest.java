package br.com.joaodartora.assemblyservice.service;

import br.com.joaodartora.assemblyservice.dto.AgendaResultsDto;
import br.com.joaodartora.assemblyservice.dto.SessionDto;
import br.com.joaodartora.assemblyservice.exception.AssociatedAlreadyVotedException;
import br.com.joaodartora.assemblyservice.exception.NoVotesFoundException;
import br.com.joaodartora.assemblyservice.repository.VoteRepository;
import br.com.joaodartora.assemblyservice.repository.entity.VoteEntity;
import br.com.joaodartora.assemblyservice.stub.SessionStub;
import br.com.joaodartora.assemblyservice.stub.VoteStub;
import br.com.joaodartora.assemblyservice.type.VoteChoiceEnum;
import br.com.joaodartora.assemblyservice.type.VotesResultEnum;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VoteServiceTest {

    private final VoteService voteService;
    private final SessionService sessionService;
    private final VoteRepository voteRepository;
    private final AssociatedService associatedService;

    public VoteServiceTest() {
        this.voteRepository = mock(VoteRepository.class);
        this.sessionService = mock(SessionService.class);
        this.associatedService = mock(AssociatedService.class);
        this.voteService = new VoteService(sessionService, voteRepository, associatedService);
    }

    @Test
    void voteWithSuccessShouldReturnNewVoteReceiptNumber() {
        doNothing().when(associatedService).verifyAssociatedPermissionToVote("24440570019");
        doNothing().when(sessionService).validateOpenSession(3131L);
        when(voteRepository.save(any(VoteEntity.class))).thenReturn(VoteStub.createEntityWithVote(VoteChoiceEnum.YES));

        Long result = voteService.vote(3131L, VoteStub.createDto());

        ArgumentCaptor<VoteEntity> entityCaptor = ArgumentCaptor.forClass(VoteEntity.class);
        verify(voteRepository, times(1)).save(entityCaptor.capture());
        VoteEntity capturedEntity = entityCaptor.getValue();

        assertAll("Assert all method fields",
                () -> assertEquals(1L, result),
                () -> assertNull(capturedEntity.getId()),
                () -> assertEquals(3131L, capturedEntity.getAgendaId()),
                () -> assertEquals(VoteChoiceEnum.YES, capturedEntity.getVote()),
                () -> assertEquals(31081998L, capturedEntity.getAssociatedId()),
                () -> assertEquals("24440570019", capturedEntity.getAssociatedCpf()));
    }

    @Test
    void voteWithDataIntegrityViolationExceptionFromDatabaseShouldThrowAssociatedAlreadyVotedException() {
        doNothing().when(associatedService).verifyAssociatedPermissionToVote("24440570019");
        doNothing().when(sessionService).validateOpenSession(3131L);
        when(voteRepository.save(any(VoteEntity.class))).thenThrow(new DataIntegrityViolationException("Duplicated key"));

        AssociatedAlreadyVotedException exception = assertThrows(AssociatedAlreadyVotedException.class, () -> voteService.vote(3131L, VoteStub.createDto()));

        assertEquals("error.associatedAlreadyVoted", exception.getMessage());
    }

    @Test
    void getResultWithSuccessShouldReturnNewAgendaResultsWithYesWinning() {
        when(sessionService.getClosedSession(3131L)).thenReturn(SessionStub.createDto());
        when(voteRepository.findAllByAgendaId(3131L)).thenReturn(VoteStub.createEntitiesListWithYesWinning());
        doNothing().when(sessionService).saveSessionResult(any(SessionDto.class), any(VotesResultEnum.class));

        AgendaResultsDto result = voteService.getResult(3131L);

        ArgumentCaptor<SessionDto> dtoCaptor = ArgumentCaptor.forClass(SessionDto.class);
        verify(sessionService, times(1)).saveSessionResult(dtoCaptor.capture(), eq(VotesResultEnum.YES));
        SessionDto capturedDto = dtoCaptor.getValue();

        assertAll("Assert all method fields",
                () -> assertAll("Assert result fields",
                        () -> assertEquals(2L, result.getYesVotes()),
                        () -> assertEquals(1L, result.getNoVotes()),
                        () -> assertEquals(3L, result.getTotalVotes()),
                        () -> assertEquals(VotesResultEnum.YES, result.getResult())),
                () -> assertAll("Assert capturedDto fields",
                        () -> assertEquals(1L, capturedDto.getId()),
                        () -> assertEquals(3131L, capturedDto.getAgendaId()),
                        () -> assertEquals("2020-11-08T09:19:37.474372", capturedDto.getStartTime().toString()),
                        () -> assertEquals("2020-11-08T09:49:37.474372", capturedDto.getEndTime().toString()),
                        () -> assertNull(capturedDto.getResult())));
    }

    @Test
    void getResultWithSuccessShouldReturnNewAgendaResultsWithNoWinning() {
        when(sessionService.getClosedSession(3131L)).thenReturn(SessionStub.createDto());
        when(voteRepository.findAllByAgendaId(3131L)).thenReturn(VoteStub.createEntitiesListWithNoWinning());
        doNothing().when(sessionService).saveSessionResult(any(SessionDto.class), any(VotesResultEnum.class));

        AgendaResultsDto result = voteService.getResult(3131L);

        verify(sessionService, times(1)).saveSessionResult(any(SessionDto.class), eq(VotesResultEnum.NO));
        assertAll("Assert result fields",
                () -> assertEquals(1L, result.getYesVotes()),
                () -> assertEquals(2L, result.getNoVotes()),
                () -> assertEquals(3L, result.getTotalVotes()),
                () -> assertEquals(VotesResultEnum.NO, result.getResult()));
    }

    @Test
    void getResultWithSuccessShouldReturnNewAgendaResultsWithDrawResult() {
        when(sessionService.getClosedSession(3131L)).thenReturn(SessionStub.createDto());
        when(voteRepository.findAllByAgendaId(3131L)).thenReturn(VoteStub.createEntitiesListWithDraw());
        doNothing().when(sessionService).saveSessionResult(any(SessionDto.class), any(VotesResultEnum.class));

        AgendaResultsDto result = voteService.getResult(3131L);

        verify(sessionService, times(1)).saveSessionResult(any(SessionDto.class), eq(VotesResultEnum.DRAW));
        assertAll("Assert result fields",
                () -> assertEquals(1L, result.getYesVotes()),
                () -> assertEquals(1L, result.getNoVotes()),
                () -> assertEquals(2L, result.getTotalVotes()),
                () -> assertEquals(VotesResultEnum.DRAW, result.getResult()));
    }

    @Test
    void getResultWithNoVotesOnTheAgendaShouldThrowNoVotesFoundException() {
        when(sessionService.getClosedSession(3131L)).thenReturn(SessionStub.createDto());
        when(voteRepository.findAllByAgendaId(3131L)).thenReturn(Collections.emptyList());

        NoVotesFoundException exception = assertThrows(NoVotesFoundException.class, () -> voteService.getResult(3131L));

        assertEquals("error.noVotesFound", exception.getMessage());
    }


}
