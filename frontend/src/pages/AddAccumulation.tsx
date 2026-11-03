import React, { useState } from 'react';
import { AccumulationForm } from 'components/AccumulationForm';
import { accumulationService } from 'api/accumulationService';
import { Accumulation } from '../types';
import { Button } from '@ui5/webcomponents-react';
import {useNavigate} from "react-router-dom";

export const AddAccumulationPage: React.FC = () => {
    const [isDialogOpen, setIsDialogOpen] = useState<boolean>(false);
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();

    const handleFormSubmit = async (formData: Accumulation) => {
        // e.preventDefault();
        setError(null);

        try {
            const createdAccumulation = await accumulationService.create(formData);
            navigate(`/accumulations/${createdAccumulation.id}`, {state: {accumulation: createdAccumulation}});
        } catch (err: any) {
            setError(err.response?.data?.message || 'Not created - error occured.');
            console.error(err);
        }
    };

    return (
        <div style={{ padding: '20px', textAlign: 'center' }}>

            {error && <div style={{ color: 'red', marginBottom: '10px' }}>{error}</div>}

            <Button
                design="Emphasized"
                onClick={() => setIsDialogOpen(true)}
            >
                Add new Accumulation
            </Button>

            <AccumulationForm
                open={isDialogOpen}
                onClose={() => setIsDialogOpen(false)}
                onSubmit={handleFormSubmit}
            />
        </div>
    );
};