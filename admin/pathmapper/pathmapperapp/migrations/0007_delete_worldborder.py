# -*- coding: utf-8 -*-
# Generated by Django 1.9.7 on 2016-07-15 05:05
from __future__ import unicode_literals

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('pathmapperapp', '0006_worldborder'),
    ]

    operations = [
        migrations.DeleteModel(
            name='WorldBorder',
        ),
    ]