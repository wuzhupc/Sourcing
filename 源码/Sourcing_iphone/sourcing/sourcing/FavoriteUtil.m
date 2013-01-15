//
//  FavoriteUtil.m
//  sourcing
//
//  Created by wuzhu on 13-1-9.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "FavoriteUtil.h"
#import "FileUtil.h"
#import "AutoCoding.h"
#import "BaseVO.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface
@interface FavoriteUtil ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation FavoriteUtil

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Public *********************************************************************/
@synthesize dataList=dataList_;
/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

static FavoriteUtil *_favoriteUtil = nil;

+(FavoriteUtil *)favoriteUtil
{
    @synchronized([FavoriteUtil class])
    {
        if(!_favoriteUtil)
        {
            _favoriteUtil = [[FavoriteUtil alloc] init];
        }
        return _favoriteUtil;
    }
}
-(id)init
{
    if(_favoriteUtil)
        return  _favoriteUtil;
    self = [super init];
    if(self)
    {
        [self initFavDataList];
    }
    return  self;
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods

-(BOOL)addFavData:(BaseVO *)kvo
{
    if(kvo==nil)
        return NO;
    NSInteger index = [self hasFavData:kvo];
    if(index>=0)
        return YES;
    [dataList_ insertObject:kvo atIndex:0];
    return [self saveFavDataList];
}

-(NSInteger)hasFavData:(BaseVO *)kvo
{
    if(self.dataList==nil||[self.dataList count]==0||kvo==nil)
        return -1;
    for (NSInteger i =0; i<[self.dataList count]; i++) {
        BaseVO *vo = [self.dataList objectAtIndex:i];
        if ([vo isEqual:kvo]) {
            return i;
        }
    }
    return -1;
}

-(BOOL)removeFavData:(BaseVO *)kvo
{
    return [self removeFavDataWithIndex:[self hasFavData:kvo]];
}

-(BOOL)removeFavDataWithIndex:(NSInteger)kindex
{
    if(kindex<0||self.dataList==nil||[self.dataList count]==0||kindex>=[self.dataList count])
        return NO;
    [dataList_ removeObjectAtIndex:kindex];
    return [self saveFavDataList];
}

-(NSInteger)getFavNumber
{
    if(self.dataList==nil)
        return 0;
    return [self.dataList count];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods
-(void)initFavDataList
{
    NSString *filepath = [FavoriteUtil getFavInfoFilePath];
    if(![[NSFileManager defaultManager] fileExistsAtPath:filepath])
    {
        dataList_ = [[NSMutableArray alloc] init];
        return;
    }
    dataList_ = [NSKeyedUnarchiver unarchiveObjectWithFile:filepath];
}

-(BOOL)saveFavDataList
{
    NSString *filepath = [FavoriteUtil getFavInfoFilePath];
    BOOL result = NO;
//    if([[NSFileManager defaultManager] fileExistsAtPath:filepath])
//    {
//        result = [[NSFileManager defaultManager] removeItemAtPath:filepath error:nil];
//    }
    if(self.dataList != nil)
    {
        return [NSKeyedArchiver archiveRootObject:self.dataList toFile:filepath];
    }
    return result;
}

+(NSString *)getFavInfoFilePath
{
    return [FileUtil createFilePathForLocation:FileDirectoryDocuments withFileName:@"datafile_fav" withExtension:@"dat"];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - XXXDataSource / XXXDelegate methods


@end
