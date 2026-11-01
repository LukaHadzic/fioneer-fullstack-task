import React, { useState } from 'react';
import { AccumulationForm } from 'components/AccumulationForm';
import { accumulationService } from 'api/accumulationService';
import { Accumulation } from '../types';
import { Button } from '@ui5/webcomponents-react';

export const AddAccumulationPage: React.FC = () => {
    const [isDialogOpen, setIsDialogOpen] = useState<boolean>(false);
    // const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string | null>(null);

    // Funkcija koju prosleđujemo u formu
    const handleFormSubmit = async (formData: Accumulation) => {
        // setLoading(true);
        setError(null);
        try {
            // Pozivamo tvoj accumulationService koji šalje podatke na bekand
            await accumulationService.create(formData);

            // Opciono: ovde možeš osvežiti listu akumulacija na stranici
            console.log('Uspesno sacuvano!');
        } catch (err: any) {
            setError(err.response?.data?.message || 'Greška prilikom čuvanja akumulacije.');
            console.error(err);
        } finally {
            // setLoading(false);
        }
    };

    return (
        <div style={{ padding: '20px' }}>
            <h1>Upravljanje akumulacijama</h1>

            {error && <div style={{ color: 'red', marginBottom: '10px' }}>{error}</div>}

            {/* Dugme koje otvara dijalog */}
            <Button
                design="Emphasized"
                onClick={() => setIsDialogOpen(true)}
            >
                Add new Accumulation
            </Button>

            {/* Forma unutar UI5 Dialog-a */}
            <AccumulationForm
                open={isDialogOpen}
                onClose={() => setIsDialogOpen(false)}
                onSubmit={handleFormSubmit}
            />
        </div>
    );
};