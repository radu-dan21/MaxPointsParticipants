package ssvv.example.validation;

import ssvv.example.domain.Tema;

public class TemaValidator implements Validator<Tema> {

    /**
     * Valideaza o tema
     * @param entity - tema pe care o valideaza
     * @throws ValidationException daca tema nu e valida
     */
    @Override
    public void validate(Tema entity) throws ValidationException {
        if (entity.getID() == null || entity.getID().equals("")) {
            throw new ValidationException("Numar tema invalid!");
        }

        if (entity.getDescriere() == null || entity.getDescriere().equals("")) {
            throw new ValidationException("Descriere invalida!");
        }

        int deadline = entity.getDeadline();
        if (deadline < 1 || deadline > 14) {
            throw new ValidationException("Deadline-ul trebuie sa fie intre 1-14!");
        }

        int primire = entity.getPrimire();
        if (primire < 1 || primire > 14) {
            throw new ValidationException("Saptamana primirii trebuie sa fie intre 1-14!");
        }

        if (primire > deadline) {
            throw new ValidationException("Saptamana primirii nu poate fi mai mare decat saptamana predarii!");
        }
    }
}
