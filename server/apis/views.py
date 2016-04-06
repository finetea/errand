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


@api_view(['GET', 'POST'])
def contract_list(request, format=None):
    """
    List all contracts, or create a new contract
    """
    if request.method == 'GET':
        contracts = Contract.objects.all()
        serializer = ContractSerializer(contracts, many=True)
        return Response(serializer.data)
    elif request.method == 'POST':
        serializer = ContractSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_request)

@api_view(['GET', 'PUT', 'DELETE'])
def contract_detail(request, pk, format=None):
    """
    Retrieve, update or delete a contract instance
    """
    try:
        contract.Contract.objects.get(pk=pk)
    except Contract.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = ContractSerializer(contract)
        return Response(serializer.data)
    elif request.method == 'PUT':
        serializer = ContractSerializer(contract, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_request)
    elif request.method == 'DELETE':
        contract.delete()
        return Respponse(status=status.HTTP_204_NO_CONTENT)
