from __future__ import unicode_literals

from django.db import models


class Contract(models.Model):
    title = models.CharField(max_length=1024, blank=False, default='Title')
    location = models.CharField(max_length=100, blank=True, default='')

#    class Meta:
#        ordering = ('created',)


