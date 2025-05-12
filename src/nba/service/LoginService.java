package nba.service;

import lombok.RequiredArgsConstructor;
import nba.dto.LoginDTO;
import nba.dto.OwnerState;
import nba.dto.RegisterDTO;
import nba.repository.OwnerRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class LoginService {
    private final OwnerRepository ownerRepository;

    public OwnerState findByIdAndPassword(String beforeParse){
        return Optional.ofNullable(loginParse(beforeParse))
                .map(ownerRepository::findByIdAndPassword)
                .orElse(null);
    }

    public boolean register(String beforeParse){
        LoginDTO loginDTO = loginParse(beforeParse);
        if(loginDTO == null){
            return false;
        }
        System.out.println(loginDTO);
        return ownerRepository.register(loginDTO) != 0;
    }

    private LoginDTO loginParse(String next) {
        try {
            String[] tokens = next.split(",");
            return  LoginDTO.builder()
                    .email(tokens[0].trim())
                    .password(tokens[1].trim())
                    .userType("User")
                    .build();
        }catch (Exception e){
//            e.printStackTrace();
            return null;
        }
    }
}
