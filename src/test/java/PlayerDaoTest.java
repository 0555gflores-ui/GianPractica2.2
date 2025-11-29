
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.daoexample.daos.PlayerDao;
import com.daoexample.models.Player;

class PlayerDaoTest {

    private PlayerDao dao;

    @BeforeEach
    void setUp() {
        dao = new PlayerDao(); // inicializa DAO
    }

    @Test
    void testSavePlayer() {
        Player player = new Player(1,"Juan", "Delantero", 20);
        boolean result = dao.createRecord(player); // Boolean if ok
        assertTrue(result, "El jugador debería guardarse correctamente");
    }

    @Test
    void testFindPlayerById() {
        Player player = dao.readRecord(4);
        assertNotNull(player, "El jugador debería existir");
        assertEquals(4, player.getId());
    }
}