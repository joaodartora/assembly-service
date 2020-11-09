package br.com.joaodartora.assemblyservice.stub;

import br.com.joaodartora.assemblyservice.dto.AssociatedDto;

public class AssociatedStub {

    public static AssociatedDto createDto() {
        AssociatedDto dto = new AssociatedDto();
        dto.setId(31081998L);
        dto.setCpf("24440570019");
        return dto;
    }

}
