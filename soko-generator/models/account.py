from dataclasses import dataclass
from datetime import datetime
from enum import Enum


@dataclass
class CompleteSignupRequest:
    first_name: str
    last_name: str
    date_of_birth: datetime
    gender: str
    country: str


class Gender(Enum):
    MALE = "Male"
    FEMALE = "Female"
    OTHER = "Other"


@dataclass
class SignupRequest:
    email_address: str
    password: str
    role: Gender


@dataclass
class LoginRequest:
    email_address: str
    password: str
