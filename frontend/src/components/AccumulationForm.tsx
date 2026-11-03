import {Accumulation} from "../types";
import {useState} from "react";
import {Bar, Button, Dialog, Form, FormItem, Input, Label} from "@ui5/webcomponents-react";



interface AccumulationFormProps {
    open: boolean;
    onClose: () => void;
    onSubmit: (data: Accumulation) => void;
}

export const AccumulationForm: React.FC<AccumulationFormProps> = ({open, onClose, onSubmit}) => {
    const [name, setName] = useState('');
    const [business, setBusiness] = useState('');
    // const[amount, setAmount] = useState('');
    const [status, setStatus] = useState('');
    const [description, setDescription] = useState('');

    const handleSubmit = () => {
        if (!name || !business || !status) return;

        onSubmit({
           name,
           business,
           // amount: parseFloat(amount),
           status,
           description,
        });

        setName('');
        setBusiness('');
        // setAmount('');
        setStatus('');
        setDescription('');
        onClose();
    };

    return (
        <Dialog
            open={open}
            // onAfterClose={onClose}
            headerText="Add new Accumulation"
            footer={
                <Bar
                    endContent={
                        <>
                            <Button design="Emphasized" onClick={handleSubmit}>Save</Button>
                            <Button design="Transparent" onClick={onClose}>Cancel</Button>
                        </>
                    }
                ></Bar>
            }
            style={{width: '400px'}}
        >
            <div style={{padding: '1 rem'}}>
                <Form>
                    <FormItem>
                        <Label slot="label" required>Name</Label>
                        <Input
                            value={name}
                            onInput={(e: any) => setName(e.target.value)}
                            placeholder="Enter name"
                            />
                    </FormItem>
                    <FormItem>
                        <Label slot="label" required>Business</Label>
                        <Input
                            value={business}
                            onInput={(e: any)=> setBusiness(e.target.value)}
                            placeholder="Enter class of business"
                        />
                    </FormItem>
                    {/*<FormItem>*/}
                    {/*    <Label slot="label" required>Amount</Label>*/}
                    {/*    <Input*/}
                    {/*        type="Number"*/}
                    {/*        value={amount}*/}
                    {/*        onInput={(e: any)=> setAmount(e.target.value)}*/}
                    {/*        placeholder="0.00"*/}
                    {/*    />*/}
                    {/*</FormItem>*/}
                    <FormItem>
                        <Label slot="label" required>Status</Label>
                        <Input
                            value={status}
                            onInput={(e: any)=> setStatus(e.target.value)}
                            placeholder="DRAFT"
                        />
                    </FormItem>
                    <FormItem>
                        <Label slot="label">Description Optional</Label>
                        <Input
                            value={description}
                            onInput={(e: any) => setDescription(e.target.value)}
                            placeholder="Description (Optional)"
                            />
                    </FormItem>
                </Form>
            </div>
        </Dialog>
    );
};