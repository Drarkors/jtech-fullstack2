package br.com.jtech.tasklist.application.ports.input.task;

/**
 * class DeleteTaskInputGateway
 * <p>
 * user rafael.zanetti
 */
public interface DeleteTaskInputGateway {

  void delete(String id, String userId);

}
