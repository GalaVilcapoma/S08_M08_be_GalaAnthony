package pe.edu.vallegrande.eduSmart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.eduSmart.model.Game;
import pe.edu.vallegrande.eduSmart.repository.GameRepository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GameService {

    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<Game> findAll() {
        return gameRepository.findByAvailableTrue();
    }

    @Transactional(readOnly = true)
    public List<Game> findAllIncludingDeleted() {
        return gameRepository.findAllIncludingDeleted();
    }

    @Transactional(readOnly = true)
    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }

    public Game save(Game game) {
        if (game.getAvailable() == null) {
            game.setAvailable(true);
        }
        return gameRepository.save(game);
    }

    public Game update(Long id, Game game) {
        return gameRepository.findById(id)
                .map(existingGame -> {
                    existingGame.setTitle(game.getTitle());
                    existingGame.setDescription(game.getDescription());
                    existingGame.setGenre(game.getGenre());
                    existingGame.setPlatform(game.getPlatform());
                    existingGame.setDeveloper(game.getDeveloper());
                    existingGame.setReleaseDate(game.getReleaseDate());
                    existingGame.setRating(game.getRating());
                    existingGame.setPrice(game.getPrice());
                    existingGame.setImageUrl(game.getImageUrl());
                    if (game.getAvailable() != null) {
                        existingGame.setAvailable(game.getAvailable());
                    }
                    return gameRepository.save(existingGame);
                })
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + id));
    }

    public void delete(Long id) {
        gameRepository.updateStatus(id, false);
    }

    public void restore(Long id) {
        gameRepository.updateStatus(id, true);
    }

    @Transactional(readOnly = true)
    public List<Game> search(String q) {
        return gameRepository.searchByQuery(q);
    }

    @Transactional(readOnly = true)
    public List<Game> findByPlatform(String platform) {
        return gameRepository.findByPlatformIgnoreCaseAndAvailableTrue(platform);
    }

    @Transactional(readOnly = true)
    public List<Game> findTopRated() {
        return gameRepository.findTopRated();
    }
}
