package com.timeline.demo.Service;

import com.timeline.demo.Dto.RegistrosDTO.RegistroDto;
import com.timeline.demo.Dto.RegistrosDTO.RegistroResponseDTO;
import com.timeline.demo.Repository.ComentsRepository;
import com.timeline.demo.Repository.RegistroRepository;
import com.timeline.demo.Repository.TimeLineRepository;
import com.timeline.demo.Repository.UsuarioRepository;
import com.timeline.demo.model.Registro.Registro;
import com.timeline.demo.model.TimeLine;
import com.timeline.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class RegistroService {

    @Autowired
    private  RegistroRepository registroRepository;

    @Autowired
    private  UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    private  ComentsRepository comentsRepository;

    @Autowired
    private TimeLineRepository timeLineRepository;


    private RegistroResponseDTO toResponseDTO(Registro registro) {
        RegistroResponseDTO dto = new RegistroResponseDTO();
        dto.setId(registro.getId());
        dto.setTitulo(registro.getTitulo());
        dto.setDescricao(registro.getDescricao());
        dto.setDataInicio(registro.getDataInicio());
        dto.setDataFim(registro.getDataFim());
        dto.setImagemUrl(registro.getImagemUrl());
        return dto;
    }

    public void validarRegistroAtivo(Registro registro){
        if (registro.isDeletado()){
            throw new RuntimeException("Registro inativo, não pode ser manpulado");
        }
    }

    public void usuarioDono(Usuario user, TimeLine line){
        if (!line.getUsuario().getId().equals(user.getId())) {
            throw new RuntimeException("Acesso negado");
        }
    }

    public Registro buscarRegistroDaTimeline(UUID registroId, UUID timelineId){
       Registro registro = registroRepository.findById(registroId).orElseThrow(() -> new RuntimeException("sem registros"));
       TimeLine timeLine = timeLineRepository.findById(timelineId).orElseThrow(() -> new RuntimeException("Timeline não encontrada"));
       if (!registro.getTimeLine().getId().equals(timeLine.getId())) {
            throw new RuntimeException("Registro não pertence a esta timeline");
        }
        return registro;
    }

    public RegistroResponseDTO criaregistro(RegistroDto dto) {

        Usuario usuario = usuarioService.getUsuarioLogado();

        Registro registro = new Registro();
        registro.setTitulo(dto.getTitulo());
        registro.setDescricao(dto.getDescricao());
        registro.setDataInicio(dto.getDataInicio());
        registro.setDataFim(dto.getDataFim());
        registro.setImagemUrl(dto.getImagemUrl());


        registro.setTimeLine(usuario.getTimeLine());
        Registro salvo = registroRepository.save(registro);
        return toResponseDTO(salvo);
    }

    public RegistroResponseDTO atualizarRegistro(UUID registroId, RegistroDto dto){
        Usuario usuario = usuarioService.getUsuarioLogado();
        TimeLine timeLine = usuario.getTimeLine();

        usuarioDono(usuario,timeLine);
        Registro registro = buscarRegistroDaTimeline(registroId,timeLine.getId());
        validarRegistroAtivo(registro);

        registro.setTitulo(dto.getTitulo());
        registro.setDescricao(dto.getDescricao());
        registro.setDataInicio(dto.getDataInicio());
        registro.setDataFim(dto.getDataFim());
        registro.setImagemUrl(dto.getImagemUrl());

        return toResponseDTO(registroRepository.save(registro));
    }

    public void moverParaLixeira(UUID registroId, UUID timelineId) {
        Usuario usuario = usuarioService.getUsuarioLogado();
        TimeLine timeLine = timeLineRepository.findById(timelineId).orElseThrow(() -> new RuntimeException("Erro na busca de timeline"));

        Registro registro = registroRepository.findById(registroId).orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        buscarRegistroDaTimeline(registroId,timelineId);
        usuarioDono(usuario,timeLine);

        registro.deletar();
        registroRepository.save(registro);
    }

    public void deletarPermanentemente(UUID registroId, UUID timelineId) {
        Usuario usuario = usuarioService.getUsuarioLogado();
        TimeLine timeLine = timeLineRepository.findById(timelineId).orElseThrow(() -> new RuntimeException("Erro na busca de timeline"));

        Registro registro = registroRepository.findById(registroId).orElseThrow(() -> new RuntimeException("Registro não encontrado"));

        buscarRegistroDaTimeline(registroId,timelineId);
        usuarioDono(usuario,timeLine);

        registroRepository.delete(registro);
    }

    public List<Registro> listarregistrosativos() {

        Usuario usuario = usuarioService.getUsuarioLogado();
        TimeLine timeLine = usuario.getTimeLine();

        if (timeLine.getRegistros() == null) {
            return List.of();
        }

        return timeLine.getRegistros()
                .stream()
                .filter(r -> !r.isDeletado())
                .toList();
    }

    public List<Registro> listarregistrosDeletados() {
        Usuario usuario = usuarioService.getUsuarioLogado();
        TimeLine timeLine = usuario.getTimeLine();
        if (timeLine.getRegistros() == null) {
            return List.of();
        }
        return timeLine.getRegistros()
                .stream()
                .filter(r -> r.isDeletado())
                .toList();
    }

    public List<Registro> getAllRegistros(){
        Usuario usuario = usuarioService.getUsuarioLogado();
        TimeLine timeLine = usuario.getTimeLine();

        if (timeLine.getRegistros() == null){
            return List.of();
        }
        return timeLine.getRegistros()
                .stream()
                .toList();
    }

    public RegistroResponseDTO buscarPorId(UUID registroId){
        Usuario usuario = usuarioService.getUsuarioLogado();

        Registro res = registroRepository.findByid(registroId)
                .orElseThrow(() -> new RuntimeException("Registro não emcontrado"));

        return toResponseDTO(res);
    }



    //=======================================================================
    //ROTAS PUBLICAS
    //=======================================================================

    public List<Registro> listarRegistrosPublicos(UUID usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        TimeLine timeLine = usuario.getTimeLine();
        return  timeLine.getRegistros()
                .stream()
                .filter(objregistros -> !objregistros.isDeletado())
                .toList();
    }
}