package paulotech.backend.product.infra.primary.exceptions;

public class EntityCreationFailed extends RuntimeException {

public EntityCreationFailed(String message) {
    super(message);
}
}
