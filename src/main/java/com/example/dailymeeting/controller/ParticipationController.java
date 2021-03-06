package com.example.dailymeeting.controller;

import com.example.dailymeeting.model.Participation;
import com.example.dailymeeting.repository.ParticipationRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/participation")
public class ParticipationController {

   private ParticipationRepository participationRepository;

    public ParticipationController(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    @GetMapping
    public List<Participation> getAllParticipations(){
        return participationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional getParticipation(@PathVariable Long id){
        return participationRepository.findById(id);
    }

    @GetMapping("/meeting/{id}")
    public List<Participation>getAllParticipationsByMeetingId(@PathVariable Long id){
        return participationRepository.findAllByMeeting_Id(id);
    }

    @GetMapping("/meeting/{mId}/appuser/{uId}")
    public Optional <Participation> getParticipationBymeetingIdAndAppuserId(@PathVariable Long mId, @PathVariable Long uId){
        return participationRepository.findAllByMeeting_IdAndAppUser_Id(mId, uId);
    }

    @PostMapping
    public void createParticipation(@RequestBody Participation newParticipation) throws InterruptedException {
        participationRepository.save(newParticipation);
    }

    @PutMapping()
    public void updateParticipation(@RequestBody Participation updateParticipation){
        System.out.println(updateParticipation.isTimeKeeper());
        participationRepository.save(updateParticipation);
    }

    @DeleteMapping("/{id}")
    public void deleteParticipation(@PathVariable Long id){
        participationRepository.deleteById(id);
    }
}
