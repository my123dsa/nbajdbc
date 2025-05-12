package nba.service;

import lombok.RequiredArgsConstructor;
import nba.dto.owner.LoginDTO;
import nba.dto.owner.OwnerState;
import nba.dto.owner.RegisterDTO;
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
        RegisterDTO registerDTO = registerParse(beforeParse);
        if(registerDTO == null){
            return false;
        }
        if(registerDTO.getTeamId()<1 || registerDTO.getTeamId()>4){
            return false;
        }
        System.out.println(registerDTO);
        return ownerRepository.register(registerDTO) != 0;
    }

    private RegisterDTO registerParse(String next) {
        try {
            String[] tokens = next.split(",");
            return  RegisterDTO.builder()
                    .email(tokens[0].trim())
                    .password(tokens[1].trim())
                    .teamId(Integer.parseInt(tokens[2].trim()))
                    .build();
        }catch (Exception e){
//            e.printStackTrace();
            return null;
        }
    }

    private LoginDTO loginParse(String next) {
        try {
            String[] tokens = next.split(",");
            return  LoginDTO.builder()
                    .email(tokens[0].trim())
                    .password(tokens[1].trim())
                    .build();
        }catch (Exception e){
//            e.printStackTrace();
            return null;
        }
    }
}
