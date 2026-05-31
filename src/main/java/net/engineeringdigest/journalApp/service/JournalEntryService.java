package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {
    // contains the business logic
    // controller --> service --> repository

    @Autowired
    private JournalEntryRepository journalEntryRepository; //It generates implementation of interface at runtime

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
        try {
            User user = userService.findByUserName(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry); //it brings the saved work from database
            user.getJournalEntries().add(saved); // adds the entries in journal entry of user database

//            user.setUsername(null); // it does not save the entry in user as no line below it will be executed

            userService.saveUser(user); // saves the user in database with new journal entry
        } catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String username){
        boolean removed = false;
        try {
            User user = userService.findByUserName(username); // find the user
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            // removes when it matches the id of user
            if(removed){
                userService.saveUser(user); // saves the entry
                journalEntryRepository.deleteById(id); // deletes the user entry
            }
        }
        catch (Exception e){
            log.error("Error ", e); // replaced by log instead of System.out.println(e)
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
        return removed;
    }

//    public List<JournalEntry> findByUsername(String username){
//
//    }
}
