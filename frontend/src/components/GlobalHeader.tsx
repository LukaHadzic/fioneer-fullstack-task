import {useNavigate} from "react-router-dom";
import {Bar, Button, Title} from "@ui5/webcomponents-react";

export const GlobalHeader: React.FC = () => {
  const navigate = useNavigate();

  return (
      <Bar

          startContent={
            <Button design = "Transparent" onClick={() => navigate('/')}>
                <Title level={"H4"}>Insurance App</Title>
            </Button>
          }
          endContent={
            <Button design = "Emphasized" onClick={() => navigate('/accumulations')}>
                Accumulations Library
            </Button>
          }
      />
  );
};