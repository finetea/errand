from django.shortcuts import render

from django.contrib.auth.models import User
from rest_framework import viewsets
from apis.serializer import UserSerializer

from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response
from apis.models import Contract
from apis.serializer import ContractSerializer



class UserViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows users to be viewed or edited.
    """
    queryset = User.objects.all()
    serializer_class = UserSerializer

class ContractViewSet(viewsets.ModelViewSet):
    queryset = Contract.objects.all()
    serializer_class = ContractSerializer




