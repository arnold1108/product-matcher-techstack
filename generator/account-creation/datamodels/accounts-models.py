from dataclasses import dataclass, asdict


@dataclass
class CompleteSignupRequest:
    first_name: str
    last_name: str

