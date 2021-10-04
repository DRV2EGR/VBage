package ru.vbage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vbage.dto.MessageDto;
import ru.vbage.dto.UserPublicDto;
import ru.vbage.entity.Message;
import ru.vbage.entity.User;

/**
 * The type Class to dto service.
 */
@Service
public class ClassToDtoService
{
    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * Convert user to user public dto user public dto.
     *
     * @param user the user
     * @return the user public dto
     */
    public UserPublicDto convertUserToUserPublicDto(User user) {
        return new UserPublicDto(
                user.getId(),
                user.getFirstName(),
                user.getSecondName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber()
        );
    }

    public MessageDto convertMessageToMessageDto(Message message) {
        return new MessageDto(message.getSender().getId(), message.getMsg_body());
    }
}
