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

    // GET /api/games
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(gameService.findAll());
    }

    // GET /api/games/all  (incluye eliminados lógicamente)
    @GetMapping("/all")
    public ResponseEntity<List<Game>> getAllGamesIncludingDeleted() {
        return ResponseEntity.ok(gameService.findAllIncludingDeleted());
    }

    // GET /api/games/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        return gameService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/games/search?q=keyword
    @GetMapping("/search")
    public ResponseEntity<List<Game>> searchGames(@RequestParam String q) {
        return ResponseEntity.ok(gameService.search(q));
    }

    // GET /api/games/platform/{platform}
    @GetMapping("/platform/{platform}")
    public ResponseEntity<List<Game>> getGamesByPlatform(@PathVariable String platform) {
        return ResponseEntity.ok(gameService.findByPlatform(platform));
    }

    // GET /api/games/top-rated
    @GetMapping("/top-rated")
    public ResponseEntity<List<Game>> getTopRatedGames() {
        return ResponseEntity.ok(gameService.findTopRated());
    }

    // POST /api/games
    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game savedGame = gameService.save(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGame);
    }

    // PUT /api/games/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        Game updatedGame = gameService.update(id, game);
        return ResponseEntity.ok(updatedGame);
    }

    // DELETE /api/games/{id}  (borrado lógico)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // PUT /api/games/restore/{id}
    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restoreGame(@PathVariable Long id) {
        gameService.restore(id);
        return ResponseEntity.noContent().build();
    }
}
