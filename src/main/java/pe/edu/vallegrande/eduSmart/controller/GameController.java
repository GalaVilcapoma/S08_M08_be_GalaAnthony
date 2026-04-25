package pe.edu.vallegrande.eduSmart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.eduSmart.model.Game;
import pe.edu.vallegrande.eduSmart.service.GameService;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GameController {

    private final GameService gameService;

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(gameService.findAll());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Game>> getAllGamesIncludingDeleted() {
        return ResponseEntity.ok(gameService.findAllIncludingDeleted());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        return gameService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Game>> searchGames(@RequestParam String q) {
        return ResponseEntity.ok(gameService.search(q));
    }

    @GetMapping("/platform/{platform}")
    public ResponseEntity<List<Game>> getGamesByPlatform(@PathVariable String platform) {
        return ResponseEntity.ok(gameService.findByPlatform(platform));
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<Game>> getTopRatedGames() {
        return ResponseEntity.ok(gameService.findTopRated());
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game savedGame = gameService.save(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGame);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        Game updatedGame = gameService.update(id, game);
        return ResponseEntity.ok(updatedGame);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restoreGame(@PathVariable Long id) {
        gameService.restore(id);
        return ResponseEntity.noContent().build();
    }
}
