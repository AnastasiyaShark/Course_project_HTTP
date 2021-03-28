package repository;

import com.example.Course.project.model.Amount;
import com.example.Course.project.model.Transfer;
import com.example.Course.project.repository.TransferRepository;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TransferRepositoryTest {

    Amount amount = new Amount(2543,"rubel");
    Transfer transferActual = new Transfer("2222222222222222","12/22","222","3333333333333333",amount);
    @Test
    public void testSaveTransfer() {

        List<Transfer> transferRepositoryActual = new ArrayList<>();
        transferRepositoryActual.add(transferActual);

        TransferRepository transferRepository = new TransferRepository();
        transferRepository.saveTransfer(transferActual);

        assertTrue(transferRepositoryActual.size() == transferRepository.size());
    }



}
