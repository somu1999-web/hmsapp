package com.hmsapp_test.service;

import com.hmsapp_test.entity.User;
import com.hmsapp_test.payload.LoginDto;
import com.hmsapp_test.payload.UserDto;
import com.hmsapp_test.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private JWTService jwtService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, JWTService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    public ResponseEntity<?> createUser(UserDto userDto) {
        Optional<User> opUsername = userRepository.findByUsername(userDto.getUsername());
        if (opUsername.isPresent()){
            return new ResponseEntity<>("Username already exists" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmail(userDto.getEmail());
        if (opEmail.isPresent()){
            return new ResponseEntity<>("Email already exists" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opMobile = userRepository.findByMobile(userDto.getMobile());
        if (opMobile.isPresent()){
            return new ResponseEntity<>("Mobile already exists" , HttpStatus.INTERNAL_SERVER_ERROR);
        }

        User user = mapToEntity(userDto);
        user.setPassword(BCrypt.hashpw(userDto.getPassword() , BCrypt.gensalt(10)));
        user.setRole("ROLE_USER");
        User save = userRepository.save(user);
        UserDto dto = mapToDto(save);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    public ResponseEntity<?> createPropertyOwner(UserDto userDto) {
        Optional<User> opUsername = userRepository.findByUsername(userDto.getUsername());
        if (opUsername.isPresent()){
            return new ResponseEntity<>("Username already exists" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmail(userDto.getEmail());
        if (opEmail.isPresent()){
            return new ResponseEntity<>("Email already exists" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opMobile = userRepository.findByMobile(userDto.getMobile());
        if (opMobile.isPresent()){
            return new ResponseEntity<>("Mobile already exists" , HttpStatus.INTERNAL_SERVER_ERROR);
        }

        User user = mapToEntity(userDto);
        user.setPassword(BCrypt.hashpw(userDto.getPassword() , BCrypt.gensalt(10)));
        user.setRole("ROLE_OWNER");
        User save = userRepository.save(user);
        UserDto dto = mapToDto(save);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    public ResponseEntity<?> createBlogManagerAccount(UserDto userDto) {
        Optional<User> opUsername = userRepository.findByUsername(userDto.getUsername());
        if (opUsername.isPresent()){
            return new ResponseEntity<>("Username already exists" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opEmail = userRepository.findByEmail(userDto.getEmail());
        if (opEmail.isPresent()){
            return new ResponseEntity<>("Email already exists" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<User> opMobile = userRepository.findByMobile(userDto.getMobile());
        if (opMobile.isPresent()){
            return new ResponseEntity<>("Mobile already exists" , HttpStatus.INTERNAL_SERVER_ERROR);
        }

        User user = mapToEntity(userDto);
        user.setPassword(BCrypt.hashpw(userDto.getPassword() , BCrypt.gensalt(10)));
        user.setRole("ROLE_BLOGMANAGER");
        User save = userRepository.save(user);
        UserDto dto = mapToDto(save);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    public UserDto mapToDto(User user){
        UserDto dto = modelMapper.map(user, UserDto.class);
        return dto;
    }
    
    public User mapToEntity(UserDto dto){
        User user = modelMapper.map(dto, User.class);
        return user;
    }

    public String verifyLogin(LoginDto loginDto) {
        Optional<User> opUser = userRepository.findByUsername(loginDto.getUsername());
        if (opUser.isPresent()){
            User user = opUser.get();
            if (BCrypt.checkpw(loginDto.getPassword() , user.getPassword())){
                String token = jwtService.generateToken(user.getUsername());
                return token;
            }else{
                return "";
            }
        }
        return "";
    }

    public UserDto getUserProfile(User user) {
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setMobile(user.getMobile());
        return dto;
    }
}
