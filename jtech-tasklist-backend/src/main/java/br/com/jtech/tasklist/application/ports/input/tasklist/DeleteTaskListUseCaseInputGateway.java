package br.com.jtech.tasklist.application.ports.input.tasklist;

/**
 * class DeleteTaskListUseCaseInputGateway
 * <p>
 * user rafael.zanetti
 */
public interface DeleteTaskListUseCaseInputGateway {

  void delete(String id, String userId);

}
